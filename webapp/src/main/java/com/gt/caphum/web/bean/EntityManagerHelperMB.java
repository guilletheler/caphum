package com.gt.caphum.web.bean;

import javax.annotation.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean
public class EntityManagerHelperMB {

	@PersistenceContext
	EntityManager em;

	public EntityManager getEm() {
		return em;
	}
}
