package com.kbremote.main.model;

public class Angle {

	public Angle() {
		
	}
	
	public Angle(String tabanca, int h1, int b1, int a1, int h2, int b2, int a2, int h3, int b3, int a3) {
		this.tabanca = tabanca;
		this.sistemAh = h1;
		this.sistemAbeta = b1;
		this.sistemAalfa = a1;
		this.sistemBh = h2;
		this.sistemBbeta = b2;
		this.sistemBalfa = a2;
		this.sistemCh = h3;
		this.sistemCbeta = b3;
		this.sistemCalfa = a3;
	}
	
	private String tabanca;
	private int sistemAh;
	private int sistemAalfa;
	private int sistemAbeta;
	private int sistemBh;
	private int sistemBalfa;
	private int sistemBbeta;
	private int sistemCh;
	private int sistemCalfa;
	private int sistemCbeta;
	
	public String getTabanca() {
		return tabanca;
	}
	public void setTabanca(String tabanca) {
		this.tabanca = tabanca;
	}
	public int getSistemAh() {
		return sistemAh;
	}
	public void setSistemAh(int sistemAh) {
		this.sistemAh = sistemAh;
	}
	public int getSistemAalfa() {
		return sistemAalfa;
	}
	public void setSistemAalfa(int sistemAalfa) {
		this.sistemAalfa = sistemAalfa;
	}
	public int getSistemAbeta() {
		return sistemAbeta;
	}
	public void setSistemAbeta(int sistemAbeta) {
		this.sistemAbeta = sistemAbeta;
	}
	public int getSistemBh() {
		return sistemBh;
	}
	public void setSistemBh(int sistemBh) {
		this.sistemBh = sistemBh;
	}
	public int getSistemBalfa() {
		return sistemBalfa;
	}
	public void setSistemBalfa(int sistemBalfa) {
		this.sistemBalfa = sistemBalfa;
	}
	public int getSistemBbeta() {
		return sistemBbeta;
	}
	public void setSistemBbeta(int sistemBbeta) {
		this.sistemBbeta = sistemBbeta;
	}
	public int getSistemCh() {
		return sistemCh;
	}
	public void setSistemCh(int sistemCh) {
		this.sistemCh = sistemCh;
	}
	public int getSistemCalfa() {
		return sistemCalfa;
	}
	public void setSistemCalfa(int sistemCalfa) {
		this.sistemCalfa = sistemCalfa;
	}
	public int getSistemCbeta() {
		return sistemCbeta;
	}
	public void setSistemCbeta(int sistemCbeta) {
		this.sistemCbeta = sistemCbeta;
	}
}
