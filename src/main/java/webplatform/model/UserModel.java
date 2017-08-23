package webplatform.model;

public class UserModel {

	private long id;
	private String nome;
	private String telefone;
	private String email;
	private String novoEmail;
	private String tipoConta;
	private String password;
	private String novoPassword;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String cep;
	private String status;

	public UserModel() {
	}

	public UserModel(long id, String nome, String telefone, String email, String tipoConta, String password,
			String logradouro, String bairro, String cidade, String cep) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.tipoConta = tipoConta;
		this.password = password;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

}
