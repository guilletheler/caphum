package com.gt.caphum.web.repo.sistema;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gt.caphum.web.model.usuarios.Usuario;

@Repository
public interface UsuarioRepo extends PagingAndSortingRepository<Usuario, Integer>,
		JpaSpecificationExecutor<Usuario> {

	Optional<Usuario> findByUsernameAndPassword(String username, String password);

	Optional<Usuario> findByNumeroDocumentoAndPassword(Long numeroDocumento, String password);

	@Query("SELECT COALESCE(MAX(u.codigo), 0) + 1 FROM Usuario u")
	Integer nextCodigo();

}
