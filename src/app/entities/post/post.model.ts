export class Post {
  constructor(
    public id?: number,
    public conteudo?: string,
    public dt_criacao?: Date,
    public dt_atualizacao?: Date,
    public tags?: string,
    public status?: boolean,
    public comentarioId?: number,
  ) {
    this.status = false;
  }
}
