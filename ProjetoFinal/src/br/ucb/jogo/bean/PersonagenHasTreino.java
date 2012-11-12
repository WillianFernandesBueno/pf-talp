package br.ucb.jogo.bean;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "Personagens_has_Treino")
@AssociationOverrides({ @AssociationOverride(name = "pk.personagem", joinColumns = @JoinColumn(name = "Personagens_idPersonagens")),
@AssociationOverride(name = "pk.treino", joinColumns = @JoinColumn(name = "Treino_idTreino")) })
public class PersonagenHasTreino {
	
	private Date dataInicial;
	private Date dataSaida;
	
	private PersonagenHasTreinoPK pk = new PersonagenHasTreinoPK();
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	@EmbeddedId
	public PersonagenHasTreinoPK getPk() {
		return pk;
	}
	public void setPk(PersonagenHasTreinoPK pk) {
		this.pk = pk;
	}
	
}
