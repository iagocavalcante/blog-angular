import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Comentario } from './comentario.model';
import { ComentarioService } from './comentario.service';

@Component({
    selector: 'jhi-comentario-detail',
    templateUrl: './comentario-detail.component.html'
})
export class ComentarioDetailComponent implements OnInit, OnDestroy {

    comentario: Comentario;
    private subscription: any;

    constructor(
        private comentarioService: ComentarioService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.comentarioService.find(id).subscribe(comentario => {
            this.comentario = comentario;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
