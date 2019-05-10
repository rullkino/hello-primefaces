package controllers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import vo.Aluno;

@ManagedBean
@ViewScoped
public class HelloMBean {
	
	private Aluno aluno;
	private List<Aluno> alunos;
	private Double media = 0.0;  
    private Map<String,String> cursosenai = new HashMap<String, String>();
	



	@PostConstruct
	public void init(){
		cursosenai = new HashMap<String, String>();
        cursosenai.put("Dev. Sistemas", "Dev. Sistemas");
        cursosenai.put("Eletrônica","Eletrônica");
        cursosenai.put("Mecatrônica","Mecatrônica");
        cursosenai.put("Administração","Administração");
		
		aluno = new Aluno();
		alunos = new ArrayList<Aluno>();
	}
	
	public boolean validar (){
		boolean validado = true;
		if(aluno.getIdade() < 16){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Erro no campo idade", ", idade não pode ser menor que 16 anos"));
			validado = false;
		} if(aluno.getNotaFinal() < 0 || aluno.getNotaFinal() > 100){
			
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Erro no campo nota final", ", nota final deve estar entre 0 e 100"));
			validado = false;
		}
		return validado = true;
	}
		
		public void salvarAluno(){	
			
		if(validar()){
		alunos.add(aluno);
		aluno = new Aluno();
		calcularMedia();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO,"Aluno inserido com sucesso",""));
			}
		}

	
	public void calcularMedia(){
		int cont = 0;
		double soma = 0.0;
		
		for(Aluno a: alunos){
			soma += a.getNotaFinal();
			cont++;
		}
		media = soma/cont;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public Double getMedia(){
		return this.media;
	}

	public String getFormatedMedia() {
		DecimalFormat f = new DecimalFormat("##.00");
		return f.format(media);
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public Map<String, String> getCursosenai() {
		return cursosenai;
	}

	public void setCursosenai(Map<String, String> cursosenai) {
		this.cursosenai = cursosenai;
	}	

}
