package com.project.dominio.servicios;

import com.project.dominio.entidades.*;
import com.project.infraestructura.RepositorioCrear;
import com.project.infraestructura.RepositorioCrearImpl;
import com.project.infraestructura.RepositorioObtener;
import com.project.presentacion.clasesAuxiliares.DatosRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Service("servicioCrear")
@Transactional
public class ServicioCrearImpl implements ServicioCrear {
    private RepositorioCrear repositorioCrear;
    private RepositorioObtener repositorioObtener;
    @Autowired
    public ServicioCrearImpl(RepositorioCrearImpl repositorioCrear, RepositorioObtener repositorioObtener){
        this.repositorioCrear=repositorioCrear;
        this.repositorioObtener = repositorioObtener;

    }

    @Override
    public void crearUsuario(DatosRegistro datos) throws IOException {
        Ciudad ciudad=repositorioObtener.obtenerCiudadPorID(datos.getIdCiudad());
        Sexo sexo= repositorioObtener.obtenerSexoPorID(datos.getIdSexo());
        Integer edad= Period.between(LocalDate.parse(datos.getFechaNacimiento()),LocalDate.now()).getYears();
        GrupoEtario grupoEtario= definirGrupoEtario(edad);
        Rol rol=repositorioObtener.obtenerRolPorNombre("Jugador");
        Nivel nivel=repositorioObtener.obtenerNivelPorNombre("Bajo");
        Usuario usuario=new Usuario(datos.getNombre(), LocalDate.parse(datos.getFechaNacimiento()),
                datos.getMail(), BCrypt.hashpw(datos.getPassword(),BCrypt.gensalt()),datos.getUsername(),
                sexo,ciudad,grupoEtario,rol,nivel);
        guardarImagen(usuario,datos.getImg());
        repositorioCrear.crearUsuario(usuario);
    }

    private void guardarImagen(Usuario usuario, MultipartFile img) throws IOException {
        String nombreDelArchivo = UUID.randomUUID().toString();
        byte[] bytes = img.getBytes();
        String nombreOriginalImagen = img.getOriginalFilename();
        usuario.setFotoPerfil(nombreOriginalImagen);

        String extensionDelArchivoSubido = nombreOriginalImagen.substring(nombreOriginalImagen.lastIndexOf("."));
        String nuevoNombreDelArchivo = nombreDelArchivo + extensionDelArchivoSubido;

        File folder = new File("src/main/webapp/resources/core/fotosPerfil");

        if(!folder.exists()){
            folder.mkdirs();
        }

        Path path = Paths.get("src/main/webapp/resources/core/fotosPerfil/" + nuevoNombreDelArchivo);

        Path imagenABorrar = Paths.get("src/main/webapp/resources/core/fotosPerfil/" + usuario.getFotoPerfil());
        Files.deleteIfExists(imagenABorrar);

        usuario.setFotoPerfil("fotosPerfil/" + nuevoNombreDelArchivo);

        Files.write(path, bytes);
    }

    private GrupoEtario definirGrupoEtario(Integer edad) {
        return (edad < 18) ? repositorioObtener.obtenerGrupoEtarioPorNombre("Nene") :
                (edad >= 18 && edad <= 60) ? repositorioObtener.obtenerGrupoEtarioPorNombre("Adolescente") :
                        repositorioObtener.obtenerGrupoEtarioPorNombre("Jubilado");
    }
}
