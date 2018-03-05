import { Component, OnInit, OnDestroy } from '@angular/core';
import { Response } from '@angular/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { EventManager, ParseLinks, PaginationUtil, AlertService } from 'ng-jhipster';

import { Comentario } from './comentario.model';
import { ComentarioService } from './comentario.service';
import { ITEMS_PER_PAGE, Principal } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-comentario',
    templateUrl: './comentario.component.html'
})
export class ComentarioComponent implements OnInit, OnDestroy {
comentarios: Comentario[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private comentarioService: ComentarioService,
        private alertService: AlertService,
        private eventManager: EventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.comentarioService.query().subscribe(
            (res: Response) => {
                this.comentarios = res.json();
            },
            (res: Response) => this.onError(res.json())
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInComentarios();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId (index: number, item: Comentario) {
        return item.id;
    }



    registerChangeInComentarios() {
        this.eventSubscriber = this.eventManager.subscribe('comentarioListModification', (response) => this.loadAll());
    }


    private onError (error) {
        this.alertService.error(error.message, null, null);
    }
}
