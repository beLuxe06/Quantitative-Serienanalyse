package de.ur.mi.qsa_tool.model;

public class Script {
	
	private String fileName = "";
	private String filePath = "";
	private String fileContent = "";
	
	public Script(String fileName, String filePath, String fileContent) {
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	
}
