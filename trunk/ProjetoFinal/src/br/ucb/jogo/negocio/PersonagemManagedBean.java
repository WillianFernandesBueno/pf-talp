package br.ucb.jogo.negocio;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.jogo.HIB.DesafioHIB;
import br.ucb.jogo.HIB.PersonagemHIB;
import br.ucb.jogo.HIB.UsuarioHIB;
import br.ucb.jogo.bean.Classe;
import br.ucb.jogo.bean.Desafio;
import br.ucb.jogo.bean.Item;
import br.ucb.jogo.bean.Personagem;
import br.ucb.jogo.bean.Usuario;
import br.ucb.jogo.service.Util;


@ManagedBean (name="personagemManagedBean")
@SessionScoped
public class PersonagemManagedBean implements Serializable{  

	private static final long serialVersionUID = 1L;
	private Personagem personagem;
	private List<Personagem> personagens;
	private List<Personagem> filtroPersonagens;
	private String imagem;
	private String nome;
	private Classe classe;
	private Item itensSelect;

	private Desafio desafio;

	public PersonagemManagedBean() {
		setPersonagem(new Personagem());
		setFiltroPersonagens(new ArrayList<Personagem>());
		PersonagemHIB p = new PersonagemHIB();
		setPersonagens(p.list());
		this.classe = new Classe();

	}


	public List<Personagem> getFiltroPersonagens() {
		return filtroPersonagens;
	}

	public void setFiltroPersonagens(List<Personagem> filtroPersonagens) {
		this.filtroPersonagens = filtroPersonagens;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Personagem getPersonagem() {
		return personagem;
	}

	public void setPersonagem(Personagem personagem) {
		this.personagem = personagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String escolheClass(){
		return "CriaPersonagem?faces-redirect=true";

	}

	public String saveClass(){

		UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB personHib = new PersonagemHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		if(personagem == null){
			personagem = new Personagem();
		}
		System.out.println("Personagem: "+personagem+" Usuario: "+userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		personagem.setClasse(classe);

		return "CriaPersonagem2?faces-redirect=true";

	}

	public String savePersonagem() {

		UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB person = new PersonagemHIB();
		personagem.setLevel(1);
		personagem.setMana(getClasse().getMana());
		personagem.setForca(getClasse().getForca());
		personagem.setAgilidade(getClasse().getAgilidade());
		personagem.setDefesa(getClasse().getDefesa());
		personagem.setCash(1000.00);
		personagem.setUsuario(userHib.findTByLogin(Util.getUserSession()));
		personagem.setClasse(getClasse());
		personagem.setAgilidade(getClasse().getAgilidade());
		personagem.setAtivo(true);
		personagem.setExperiencia(1);
		personagem.setSituacaoDuel(1);
		person.save(personagem);
		return "IndexUsuario?faces-redirect=true";

	}



	public String encaminha(){
		UsuarioHIB userHib = new UsuarioHIB();
		DesafioHIB desafioHIB = new DesafioHIB();
		personagem = userHib.findTByLogin(Util.getUserSession()).getPersonagem();		
		if(personagem == null){
			return "/usuario/CriaPersonagem?faces-redirect=true";
		}
		desafio = desafioHIB.findByDesafio(personagem.getIdPersonagens());

		System.out.println(desafio);
		return "/usuario/PerfilPersonagem?faces-redirect=true";


	}
	public List<Personagem> getPersonagens() {
		return personagens;
	}

	public void setPersonagens(List<Personagem> personagens) {
		this.personagens = personagens;
	}

	public Item getItensSelect() {		
		return itensSelect;
	}

	public void setItensSelect(Item itens) {
		this.itensSelect = itens;
	}

	public String compraItem(){

		UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB personHib = new PersonagemHIB();
		Usuario user = userHib.findTByLogin(Util.getUserSession());
		personagem = personHib.findTByIdUser(user.getIdUsuarios());
		if(personagem.getItens() == null && personagem.getItens().isEmpty()){
			personagem.setItens(new ArrayList<Item>());
		} 		
		if(Util.verificaItemDispo(personagem, itensSelect)){
			personagem.getItens().add(itensSelect);	
			atualiItem();
			personHib.save(personagem);
		}return "PerfilPersonagem?faces-redirect=true";
	}	

	public void atualiItem(){
		personagem.setCash(personagem.getCash() - itensSelect.getPreco());
		personagem.setAgilidade(personagem.getAgilidade() + itensSelect.getAgilidade());
		personagem.setForca(personagem.getForca() + itensSelect.getForca());
		personagem.setDefesa(personagem.getDefesa() + itensSelect.getDefesa());
		personagem.setMana(personagem.getMana() + itensSelect.getMana());
	}

	public String getCriaPersonagem(){

		Usuario user = new UsuarioHIB().findTByLogin(Util.getUserSession());
		if(user.getPersonagem() == null)
			return "true";
		return "false";
	}
	public String getCriaPersonagem2(){
		Usuario user = new UsuarioHIB().findTByLogin(Util.getUserSession());
		PersonagemHIB personHib = new PersonagemHIB();
		UsuarioHIB userHib = new UsuarioHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());

		if(user.getPersonagem() != null)
		{
			if(personagem.getForca() > personagem.getDefesa() && personagem.getForca() > personagem.getAgilidade() && personagem.getForca() > personagem.getMana())
			{
				setImagem("ataque.png");
			}else if(personagem.getDefesa() > personagem.getMana() && personagem.getDefesa() > personagem.getAgilidade())
			{
				setImagem("defesa.png");
			}else if(personagem.getMana() > personagem.getAgilidade())
			{
				setImagem("magia.png");
			}else
			{
				setImagem("agilidade.png");
			}
			return "true";
		}
		return "false";
	}


	public String url(){
		UsuarioHIB userHib = new UsuarioHIB();
		PersonagemHIB personHib = new PersonagemHIB();
		personagem = personHib.findTByIdUser(userHib.findTByLogin(Util.getUserSession()).getIdUsuarios());
		if(personagem.getAtivo()==false){
			return "Trabalhando?faces-redirect=true";
		}
		DesafioHIB desafioHIB = new DesafioHIB();
		
		desafio = desafioHIB.findByDesafio(personagem.getIdPersonagens());
		
		return "/usuario/PerfilPersonagem?faces-redirect=true";
	}


	public Desafio getDesafio() {
		return desafio;
	}


	public void setDesafio(Desafio desafio) {
		this.desafio = desafio;
	}

}  