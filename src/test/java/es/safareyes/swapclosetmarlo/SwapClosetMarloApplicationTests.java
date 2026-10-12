package es.safareyes.swapclosetmarlo;

import es.safareyes.swapclosetmarlo.modelos.*;
import es.safareyes.swapclosetmarlo.repositorios.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@SpringBootTest
class SwapClosetMarloApplicationTests {

    @Test
    void contextLoads() {

        //List<Continente> todos = repository.findAllByNombreContainingAndOrdenIsLessThan("america",4);

    }

    @Autowired
    IPrendaRepository prendaRepository;
    @Autowired
    IPerfilRepository perfilRepository;
    @Autowired
    ISolicitudesRepository solicitudesRepository;
    @Autowired
    IMensajesRepository mensajesRepository;



    @Test
    void buscarPorCiudad() {

        Page<Prenda> resultado =
                prendaRepository.findByPerfil_CiudadIgnoreCase(
                        "Sevilla",
                            PageRequest.of(0, 10)
                );

        System.out.println("Total de prendas: "
                + resultado.getTotalElements());

        resultado.getContent().forEach(System.out::println);
    }


    @Test
    void buscarPorCategoria() {
        Page<Prenda> resultado = prendaRepository.findByCategoria_Id(
                1,
                PageRequest.of(0, 10)
        );

        System.out.println("Prendas por categoría: "
                + resultado.getTotalElements());
        resultado.getContent().forEach(System.out::println);
    }

    @Test
    void buscarPorTalla() {
        Page<Prenda> resultado = prendaRepository.findByTallaIgnoreCase(
                "M",
                PageRequest.of(0, 10)
        );

        System.out.println("Prendas por talla: "
                + resultado.getTotalElements());
        resultado.getContent().forEach(System.out::println);
    }

    @Test
    void buscarPorEstado() {
        Page<Prenda> resultado = prendaRepository.findByEstado(
                Estado.disponible,
                PageRequest.of(0, 10)
        );

        System.out.println("Prendas por estado: "
                + resultado.getTotalElements());
        resultado.getContent().forEach(System.out::println);
    }

    @Test
    void buscarPorModalidad() {
        Page<Prenda> resultado = prendaRepository.findByTipo(
                1,
                PageRequest.of(0, 10)
        );

        System.out.println("Prendas por modalidad: "
                + resultado.getTotalElements());
        resultado.getContent().forEach(System.out::println);
    }

    @Test
    void buscarPorEstilo() {
        Page<Prenda> resultado =
                prendaRepository.findDistinctByPerfil_Estilos_Id(
                        1,
                        PageRequest.of(0, 10)
                );

        System.out.println("Prendas por estilo: "
                + resultado.getTotalElements());

        resultado.getContent().forEach(System.out::println);
    }




    @Test
    void probarDetallePrenda() {
        Prenda prenda = prendaRepository.findDetalleById(1);

        System.out.println(prenda);

        System.out.println(prenda);
        System.out.println("Tiene estas fotos"+prenda.getFotos().size());
        System.out.println(prenda.getPerfil().getSolicitudes().getValoracion().getEstrellitas());
    }





    @Test
    void categoriasConNumeroPrendasDisponibles() {
        List<PrendasPorCategorias> resultados =
                prendaRepository.contarPrendasDisponiblesPorCategoria(
                        Estado.disponible
                );

        for (PrendasPorCategorias resultado : resultados) {
            System.out.println(
                    "Categoría: " + resultado.getNombreCategoria()
                            + " | Prendas disponibles: "
                            + resultado.getNumeroPrendas()
            );
        }
    }




    @Test
    @Transactional
    void consultarPerfilPublico() {
        Perfil perfil = perfilRepository.findById(1)
                .orElseThrow();

        System.out.println("Nombre: " + perfil.getNombre());
        System.out.println("Talla: " + perfil.getTalla());
        System.out.println("Estilos: " + perfil.getEstilos());

        List<Prenda> prendas =
                prendaRepository.findByPerfil_Id(perfil.getId());

        for (Prenda prenda : prendas) {
            System.out.println("Prenda: " + prenda.getNombre());
            System.out.println("Talla de la prenda: " + prenda.getTalla());
            System.out.println("Estado: " + prenda.getEstado());
        }
        System.out.println("Solicitud del perfil: "
                + perfil.getSolicitudes());

        if (perfil.getSolicitudes() != null
                && perfil.getSolicitudes().getValoracion() != null) {

            Integer estrellas = perfil.getSolicitudes()
                    .getValoracion()
                    .getEstrellitas();

            System.out.println("Reputación: " + estrellas + " / 5 ⭐");

        } else {
            System.out.println("Reputación: Sin valoraciones");
        }
    }







    @Test
    @Transactional
    void consultarPerfilesRecomendados() {

        List<PerfilRecomendado> perfiles =
                perfilRepository.buscarPerfilesRecomendados(1);

        for (PerfilRecomendado perfil : perfiles) {
            System.out.println("ID: " + perfil.getId());
            System.out.println("Nombre: " + perfil.getNombre());
            System.out.println("Apellido: " + perfil.getApellido1());
            System.out.println(
                    "Estilos en común: " + perfil.getEstilosComunes());
            System.out.println(
                    "Reputación media: " + perfil.getReputacionMedia());

            System.out.println("-------------------------");
        }
    }






        @Test
        void probarSolicitudesEnviadas() {
            List<Solicitudes> solicitudes =
                    solicitudesRepository.buscarSolicitudesEnviadas(1);

            System.out.println("Solicitudes enviadas: "+ solicitudes.size());
        }

        @Test
        void probarSolicitudesRecibidas() {
            List<Solicitudes> solicitudes =
                    solicitudesRepository.buscarSolicitudesRecibidas(1);

            System.out.println("Solicitudes recibidas: "+ solicitudes.size());
        }


        @Test
        void probarMensajesPaginados() {
            var pagina = mensajesRepository.buscarMensajesPorSolicitud(
                    6,
                    PageRequest.of(0, 10)
            );

            System.out.println("Mensajes: " + pagina.getTotalElements());
            pagina.getContent().forEach(System.out::println);
            System.out.println("Total de páginas: " + pagina.getTotalPages());
            System.out.println("¿Hay página siguiente?: " + pagina.hasNext());
        }




//    @Test
//    void guardarPrendaBorrador() {
//        Prenda prenda = new Prenda();
//        prenda.setNombre("Camiseta");
//        prenda.setTalla("M");
//        prenda.setMarca("Nike");
//        prenda.setEstado(Estado.Borrador);
//
//        // Asignar perfil
//        Perfil perfil = perfilRepository.findById(1)
//                .orElseThrow();
//        prenda.setPerfil(perfil);
//
//        // Asignar categoría
//        Categoria categoria = categoriaRepository.findById(1)
//                .orElseThrow();
//        prenda.setCategoria(categoria);
//
//        // Guardar cuando ya tiene perfil y categoría
//        Prenda guardada = prendaRepository.save(prenda);
//
//        assertNotNull(guardada.getId());
//        assertEquals(Estado.Borrador, guardada.getEstado());
//
//        System.out.println("Prenda guardada correctamente");
//        System.out.println("ID: " + guardada.getId());
//        System.out.println("Nombre: " + guardada.getNombre());
//        System.out.println("Estado: " + guardada.getEstado());
//
//    }




















    // @Test
    //void consultarEspecies() {

    //List<Especies> todos = especie.Repository.buscarEspeciePorNombreTipoAgua("Agua dulce");
    //}//
}
