package com.disenaclick.disenaclick;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.disenaclick.disenaclick.model.Categoria;
import com.disenaclick.disenaclick.model.Pagina;
import com.disenaclick.disenaclick.model.Plantilla;
import com.disenaclick.disenaclick.model.Rol;
import com.disenaclick.disenaclick.model.RolUsuario;
import com.disenaclick.disenaclick.model.SeccionPagina;
import com.disenaclick.disenaclick.model.Usuario;
import com.disenaclick.disenaclick.repository.CategoriaRepository;
import com.disenaclick.disenaclick.repository.PaginaRepository;
import com.disenaclick.disenaclick.repository.PlantillaRepository;
import com.disenaclick.disenaclick.repository.RolRepository;
import com.disenaclick.disenaclick.repository.RolUsuarioRepository;
import com.disenaclick.disenaclick.repository.SeccionPaginaRepository;
import com.disenaclick.disenaclick.repository.UsuarioRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PaginaRepository paginaRepository;

    @Autowired
    private PlantillaRepository plantillaRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    private SeccionPaginaRepository seccionPaginaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            Categoria categoria = new Categoria();
            categoria.setId(i + 1);
            categoria.setTipo(faker.company().industry());
            categoria.setNombreNegocio(faker.company().name());
            categoria.setUrleNegocio(faker.internet().url());
            categoriaRepository.save(categoria);
        }
        List<Categoria> categorias = categoriaRepository.findAll();

        for (int i = 0; i < 5; i++) {
            Plantilla plantilla = new Plantilla();
            plantilla.setId(i + 1);
            plantilla.setNombrePlantilla(
                    faker.options().option("Ventas", "Portafolio", "Artistico", "Naturaleza", "Cyber"));
            plantilla.setColor(faker.color().name());
            plantilla.setEnlace(faker.internet().url());
            plantillaRepository.save(plantilla);
        }

        List<Plantilla> plantillas = plantillaRepository.findAll();

        for (int i = 0; i < 7; i++) {
            Rol rol = new Rol();
            rol.setId(i + 1);
            rol.setNombreRol(faker.options().option("Dueño", "Editor", "Visita", "Moderador", "Colaborador",
                    "Administrador", "Diseñador"));
            rolRepository.save(rol);
        }

        List<Rol> rols = rolRepository.findAll();

        for (int i = 0; i < 2; i++) {
            SeccionPagina seccionPagina = new SeccionPagina();
            seccionPagina.setId(i + 1);
            seccionPagina.setTitulo(faker.company().name());
            seccionPagina.setParrafo(faker.lorem().paragraph());
            seccionPaginaRepository.save(seccionPagina);
        }

        List<SeccionPagina> seccionPaginas = seccionPaginaRepository.findAll();

        for (int i = 0; i < 7; i++) {
            RolUsuario rolUsuario = new RolUsuario();
            rolUsuario.setId(i + 1);
            rolUsuario
                    .setNombreRolUsuario(faker.options().option("Dueño", "Editor", "Visita", "Moderador", "Colaborador",
                            "Administrador", "Diseñador"));
            rolUsuarioRepository.save(rolUsuario);
        }

        List<RolUsuario> rolUsuarios = rolUsuarioRepository.findAll();  

        for (int i = 0; i < 100; i++) {
            Usuario usuario = new Usuario();
            usuario.setId(i + 1);
            usuario.setCorreo(faker.internet().emailAddress());
            usuario.setPassword(faker.internet().password());
            usuario.setNombres(faker.name().firstName() + " " + faker.name().firstName());
            usuario.setApellidos(faker.name().lastName() + " " + faker.name().lastName());
            usuario.setPaginaUsuario(faker.internet().url());
            usuario.setRol(rols.get(random.nextInt(rols.size())));
            usuario.setRolUsuario(rolUsuarios.get(random.nextInt(rolUsuarios.size())));
            usuario.setPlantilla(plantillas.get(random.nextInt(plantillas.size())));
            usuarioRepository.save(usuario);
        }

        List<Usuario> usuarios = usuarioRepository.findAll();

        for (int i = 0; i < 50; i++) {
            Pagina pagina = new Pagina();
            pagina.setId(i + 1);
            pagina.setNombrePagina(faker.company().name());
            pagina.setFechaCreacion(LocalDate.of(2025, 3, 1));
            pagina.setUsuario(usuarios.get(random.nextInt(usuarios.size())));
            pagina.setPlantilla(plantillas.get(random.nextInt(plantillas.size())));
            paginaRepository.save(pagina);
        }

        List<Pagina> paginas = paginaRepository.findAll();
    }

}
