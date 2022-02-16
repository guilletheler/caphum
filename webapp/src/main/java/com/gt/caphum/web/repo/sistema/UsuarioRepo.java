package com.gt.caphum.web.repo.sistema;

import java.util.List;
import java.util.Optional;

import com.gt.caphum.web.model.usuarios.UserRol;
import com.gt.caphum.web.model.usuarios.Usuario;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends PagingAndSortingRepository<Usuario, Integer>,
		JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByUsernameAndPassword(String username, String password);

	Optional<Usuario> findByNumeroDocumentoAndPassword(Long numeroDocumento, String password);

	List<Usuario> findByRoles(UserRol rol);

	@Query("SELECT COALESCE(MAX(u.codigo), 0) + 1 FROM Usuario u")
	Integer nextCodigo();

}
