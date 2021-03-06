package beans;

public class BeanProduto {

	private Long id;
	private String nome;
	private Integer codigo;
	private float preco;
	private float estoque;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public float getEstoque() {
		return estoque;
	}
	public void setEstoque(float estoque) {
		this.estoque = estoque;
	}
	//estes 2 gets servem para quando editar os produtos a m�scara do money n�o alterar os valores
	public String getValorTexto() {
		return Float.toString(preco).replace('.',',');
	}
	public String getEstoqueTexto() {
		return Float.toString(estoque).replace('.', ',');
	}
}
