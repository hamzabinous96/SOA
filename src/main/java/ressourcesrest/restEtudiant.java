package ressourcesrest;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("etudiants")
public class restEtudiant {

    public static EtudiantBusiness etdB = new EtudiantBusiness();
    public static OptionBusiness optB = new OptionBusiness();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {

        if (etudiant == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if (etdB.addEtudiant(etudiant)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {

        List<Etudiant> liste = etdB.getAllEtudiants();

        if (liste != null && !liste.isEmpty()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(liste)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(
            @PathParam("id") String id) {

        Etudiant etudiant = etdB.getEtudiantByIdentifiant(id);

        if (etudiant != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(etudiant)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteEtudiant(
            @PathParam("id") String id) {

        if (etdB.deleteEtudiant(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("id") String id,
            Etudiant etudiant) {

        if (etudiant == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        etudiant.setIdentifiant(id);

        if (etdB.updateEtudiant(id, etudiant)) {
            return Response.status(Response.Status.OK).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @GET
    @Path("option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(
            @QueryParam("codeOption") int codeOption) {

        Option option = optB.getOptionByCode(codeOption);

        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Etudiant> liste = etdB.getEtudiantsByOption(option);

        if (liste == null || liste.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(liste)
                .build();
    }
}