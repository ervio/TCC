package webplatform.model;

public class UserModel {

	private long id;
	private String nome;
	private String email;
	private String tipoConta;
	private String password;

	private String sobrenome;
	private String genero;
	private String especialidade;
	private String nomeInstituicao;
	private String confirmPassword;

	private String novoEmail;
	private String novoPassword;
	private String status;

	public UserModel() {
	}

	public UserModel(long id, String nome, String email, String tipoConta, String password) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipoConta = tipoConta;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNovoPassword() {
		return novoPassword;
	}

	public void setNovoPassword(String novoPassword) {
		this.novoPassword = novoPassword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNovoEmail() {
		return novoEmail;
	}

	public void setNovoEmail(String novoEmail) {
		this.novoEmail = novoEmail;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
