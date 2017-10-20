package com.example;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import metier.entities.Compte;
import metier.session.IBanqueRemote;

public class ClientRemote {

	public static void main(String[] args) {

		try {
			Context ctx = new InitialContext();
			String appName = "BanqueEAR";
			String moduleName = "BanqueEJB";
			String beanName = "BK";
			String remoteInterface = IBanqueRemote.class.getName();
			IBanqueRemote proxy = (IBanqueRemote) ctx.lookup("ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface);
			proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());
			proxy.addCompte(new Compte());
			
			Compte cp = proxy.getCompte(1L);
			System.out.println("Solde= " + cp.getSolde());
			
			proxy.verser(cp.getCode(), 150);
			System.out.println("Solde= " + proxy.getCompte(1L).getSolde());
			
			proxy.virement(1L, 2L, 1000);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
