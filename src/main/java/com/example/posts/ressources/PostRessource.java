package com.example.posts.ressources;
import com.example.posts.model.Post;
import com.example.posts.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/posts")
public class PostRessource {
    PostService postService = new PostService();
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllPosts(){
        List<Post> posts = postService.fetchAllPosts();
        return Response
                .status(Response.Status.FOUND)
                .entity(posts)
                .build();
    }
    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getPost(@PathParam("id") int id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return Response
                    .status(Response.Status.FOUND)
                    .entity(post)
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createPost(Post post){
        System.out.println(post.getAuthor()+"/"+post.getContent()+"/"+post.getTitle());
        postService.createPost(post);
        //System.out.println(test);
        return Response
                .status(Response.Status.CREATED)
                .entity(post)
                .build();
    }
    @DELETE
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deletePost(@PathParam("id") int id){
        Post post = postService.getPostById(id);
        postService.deletePost(post);
        return Response
                .status(Response.Status.CREATED)
                .entity(post)
                .build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response updatePost(@PathParam("id") int id,Post updatedPost){
        Post post = postService.getPostById(id);
        System.out.println(post.getId()+"/"+ post.getTitle());
        postService.update(updatedPost);
        System.out.println(post.getId()+"/"+ post.getTitle());
        return Response
                .status(Response.Status.CREATED)
                .entity(post)
                .build();
    }
}
