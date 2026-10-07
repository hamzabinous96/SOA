package ressourcesrest;
import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("options")

public class restOption {
   public static OptionBusiness optB=new OptionBusiness();

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public  Response getAllOption(@QueryParam("domaine") String D ){
      List<Option>l = new ArrayList<Option>();
      if(D == null){
         l=optB.getListeOptions();}
      else{
         l=optB.getOptionsByDomaine(D);}
      if(l.isEmpty()){
         return Response.status(Response.Status.NO_CONTENT).build();}
      return Response.status(200).entity(l).build();

   }
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response addOption(Option op){
      if (optB.addOption(op)){
         return Response.status(200).build();
      }
      else {
         return Response.status(404).build();
      }
   }
   @PUT
   @Path("{code}")
   @Consumes(MediaType.APPLICATION_JSON)
   public Response updateOption( @PathParam("code") int code , Option OP){

      if (optB.updateOption(code,OP)) {
         return Response.status(200).build();
      }
      else {
         return Response.status(404).build();
      }

   }
   @DELETE
   @Path("{code}")

   public Response deleteOption(@PathParam("code") int code){
      if (optB.deleteOption(code)){
         return Response.status(204).build();
      }
      else {
         return Response.status(404).build();
      }

   }
   @PUT
   @Path("{semestre}")
   @Produces(MediaType.APPLICATION_JSON)
   public  Response getAllOption(@QueryParam("semestre") int semestre ){
      List<Option>l = new ArrayList<Option>();
      if(semestre == 0){
         l=optB.getListeOptions();
         return Response.status(404).build();}
      else{
         l=optB.getOptionsBySemestre(semestre);}

      return Response.status(200).entity(l).build();

   }
}