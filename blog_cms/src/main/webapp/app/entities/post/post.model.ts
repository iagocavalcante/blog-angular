export class Post {
    constructor(
        public id?: number,
        public conteudo?: string,
        public dtcriacao?: any,
        public dtatualizacao?: any,
        public tags?: string,
        public status?: boolean,
        public comentarioId?: number,
    ) {
        this.status = false;
    }
}
