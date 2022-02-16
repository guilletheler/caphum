package com.gt.caphum.web.infra;

public interface ICodeGenerator {
    public Integer getNextCodigo();
    public void setNextCodigo(Integer nextCodigo);
    public Integer incrementarCodigo();
}
