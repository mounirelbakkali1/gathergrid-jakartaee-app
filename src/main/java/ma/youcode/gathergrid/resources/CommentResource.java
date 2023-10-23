package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.domain.Comment;
import ma.youcode.gathergrid.dto.CommentDto;
import ma.youcode.gathergrid.service.CommentService;
import ma.youcode.gathergrid.utils.Response;

import java.security.Principal;
import java.util.List;

@Path("/comments")
@NoArgsConstructor
public class CommentResource {

    private CommentService commentService ;
    private SecurityContext securityContext;

    @Inject
    public CommentResource(CommentService commentService, SecurityContext securityContext) {
        this.commentService = commentService;
        this.securityContext = securityContext;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response<CommentDto> addComment(CommentDto commentDto){
        System.out.println(commentDto.toString());
        Principal principal = securityContext.getUserPrincipal();
        if(principal == null){
            return Response.<CommentDto>builder()
                    .error(List.of(new Error("Registration is required to comment")))
                    .build();
        }
        commentDto.setUserName(principal.getName());
        return commentService.save(commentDto);
    }
}
