package com.lanche.boundary.dao;

public interface DAOCrud<T> {
	public abstract T searchByID(int id);
	public abstract boolean delete(T t);
	public abstract boolean persist(T t);
}