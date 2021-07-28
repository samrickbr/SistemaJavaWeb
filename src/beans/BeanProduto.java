package beans;

public class BeanProduto {

	private Long id;
	private String nome;
	private Integer barras;
	private float preco;
	private float estoque;
	private boolean ativo;
	private Long categoria_id;
	
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	public Long getCategoria_id() {
		return categoria_id;
	}	
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
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
	public Integer getBarras() {
		return barras;
	}
	public void setBarras(Integer barras) {
		this.barras = barras;
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
	
	//estes 2 gets servem para quando editar os produtos a mascara do money n�o alterar os valores
	public String getValorTexto() {
		return Float.toString(preco).replace('.',',');
	}
	public String getEstoqueTexto() {
		return Float.toString(estoque).replace('.', ',');
	}
}
