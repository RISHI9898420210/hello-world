package com.org.kpit.util;

import java.nio.file.Paths;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

public class GitClone {

	public static void main(String args[])
	{
		String repoUrl = "https://github.com/salesteam123/ReqMan.git.";
		String cloneDirectoryPath = "D://GITCLONE1//ReqMan"; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
		try {
		    System.out.println("Cloning "+repoUrl+" into "+repoUrl);
		    Git.cloneRepository()
		        .setURI(repoUrl)
		        .setDirectory(Paths.get(cloneDirectoryPath).toFile())
		        .call();
		    System.out.println("Completed Cloning");
		} catch (GitAPIException e) {
		    System.out.println("Exception occurred while cloning repo");
		    e.printStackTrace();
		}
	}
}
