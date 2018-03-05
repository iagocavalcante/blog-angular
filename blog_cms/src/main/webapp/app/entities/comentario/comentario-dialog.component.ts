import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Comentario } from './comentario.model';
import { ComentarioPopupService } from './comentario-popup.service';
import { ComentarioService } from './comentario.service';
import { Post, PostService } from '../post';

@Component({
    selector: 'jhi-comentario-dialog',
    templateUrl: './comentario-dialog.component.html'
})
export class ComentarioDialogComponent implements OnInit {

    comentario: Comentario;
    authorities: any[];
    isSaving: boolean;

    posts: Post[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private comentarioService: ComentarioService,
        private postService: PostService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.postService.query().subscribe(
            (res: Response) => { this.posts = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.comentario.id !== undefined) {
            this.comentarioService.update(this.comentario)
                .subscribe((res: Comentario) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.comentarioService.create(this.comentario)
                .subscribe((res: Comentario) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Comentario) {
        this.eventManager.broadcast({ name: 'comentarioListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError (error) {
        this.isSaving = false;
        this.onError(error);
    }

    private onError (error) {
        this.alertService.error(error.message, null, null);
    }

    trackPostById(index: number, item: Post) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-comentario-popup',
    template: ''
})
export class ComentarioPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private comentarioPopupService: ComentarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.comentarioPopupService
                    .open(ComentarioDialogComponent, params['id']);
            } else {
                this.modalRef = this.comentarioPopupService
                    .open(ComentarioDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
