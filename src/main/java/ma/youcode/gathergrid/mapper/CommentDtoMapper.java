package ma.youcode.gathergrid.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import ma.youcode.gathergrid.domain.Comment;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.dto.CommentDto;

@ApplicationScoped
public class CommentDtoMapper {

    public CommentDto toDto(Comment comment){
        return CommentDto.builder()
                .eventId(comment.getEvent().getId())
                .UserName(comment.getUser().getUsername())
                .content(comment.getContent())
                .build();
    }

    public Comment toEntity(CommentDto commentDto){
        return Comment.builder()
                .content(commentDto.getContent())
                .user(User.builder().username(commentDto.getUserName()).build())
                .event(Event.builder().id(commentDto.getEventId()).build())
                .build();
    }
}
