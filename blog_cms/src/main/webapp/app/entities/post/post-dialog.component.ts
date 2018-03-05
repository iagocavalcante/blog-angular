import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager, AlertService } from 'ng-jhipster';

import { Post } from './post.model';
import { PostPopupService } from './post-popup.service';
import { PostService } from './post.service';
import { Comentario, ComentarioService } from '../comentario';

@Component({
    selector: 'jhi-post-dialog',
    templateUrl: './post-dialog.component.html'
})
export class PostDialogComponent implements OnInit {

    post: Post;
    authorities: any[];
    isSaving: boolean;

    comentarios: Comentario[];
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private postService: PostService,
        private comentarioService: ComentarioService,
        private eventManager: EventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.authorities = ['ROLE_USER', 'ROLE_ADMIN'];
        this.comentarioService.query().subscribe(
            (res: Response) => { this.comentarios = res.json(); }, (res: Response) => this.onError(res.json()));
    }
    clear () {
        this.activeModal.dismiss('cancel');
    }

    save () {
        this.isSaving = true;
        if (this.post.id !== undefined) {
            this.postService.update(this.post)
                .subscribe((res: Post) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        } else {
            this.postService.create(this.post)
                .subscribe((res: Post) =>
                    this.onSaveSuccess(res), (res: Response) => this.onSaveError(res.json()));
        }
    }

    private onSaveSuccess (result: Post) {
        this.eventManager.broadcast({ name: 'postListModification', content: 'OK'});
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

    trackComentarioById(index: number, item: Comentario) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-post-popup',
    template: ''
})
export class PostPopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private postPopupService: PostPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            if ( params['id'] ) {
                this.modalRef = this.postPopupService
                    .open(PostDialogComponent, params['id']);
            } else {
                this.modalRef = this.postPopupService
                    .open(PostDialogComponent);
            }

        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
