package ma.youcode.gathergrid.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.domain.Comment;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.dto.CommentDto;
import ma.youcode.gathergrid.mapper.CommentDtoMapper;
import ma.youcode.gathergrid.repositories.CommentRepository;
import ma.youcode.gathergrid.utils.Response;

import java.util.List;
import java.util.Optional;

@Transactional
public class CommentService {

    private CommentRepository commentRepository;
    private EventService eventService;
    private UserService userService;
    private CommentDtoMapper commentDtoMapper;
    @Inject
    public CommentService(CommentRepository commentRepository, EventService eventService, UserService userService, CommentDtoMapper commentDtoMapper) {
        this.commentRepository = commentRepository;
        this.eventService = eventService;
        this.userService = userService;
        this.commentDtoMapper = commentDtoMapper;
    }

    public CommentService() {
    }

    public Response<List<Comment>> findCommentByEventId(Long eventId) {
        if(eventService.getEventById(eventId).isEmpty()){
            return Response
                    .<List<Comment>>builder()
                    .error(List.of(new Error("Event not found")))
                    .build();
        }
        return Response
                .<List<Comment>>builder()
                .result(commentRepository.findAllByEvent(eventId))
                .build();
    }

    public Response<CommentDto> save(CommentDto commentDto) {
        Optional<Event> eventById = eventService.getEventById(commentDto.getEventId());
        if(eventById.isEmpty()){
            return Response
                    .<CommentDto>builder()
                    .error(List.of(new Error("Event not found")))
                    .build();
        }
        Optional<User> user = userService.findUserByUsername(commentDto.getUserName());
        if (user.isEmpty()){
            return Response
                    .<CommentDto>builder()
                    .error(List.of(new Error("Login is required to submit a comment")))
                    .build();
        }
        Comment comment = commentDtoMapper.toEntity(commentDto);
        comment.setUser(user.get());
        comment.setEvent(eventById.get());
        commentRepository.save(comment);
        return Response
                .<CommentDto>builder()
                .result(commentDto)
                .build();
    }
}
