import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { EventManager } from 'ng-jhipster';

import { Comentario } from './comentario.model';
import { ComentarioPopupService } from './comentario-popup.service';
import { ComentarioService } from './comentario.service';

@Component({
    selector: 'jhi-comentario-delete-dialog',
    templateUrl: './comentario-delete-dialog.component.html'
})
export class ComentarioDeleteDialogComponent {

    comentario: Comentario;

    constructor(
        private comentarioService: ComentarioService,
        public activeModal: NgbActiveModal,
        private eventManager: EventManager
    ) {
    }

    clear () {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete (id: number) {
        this.comentarioService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'comentarioListModification',
                content: 'Deleted an comentario'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-comentario-delete-popup',
    template: ''
})
export class ComentarioDeletePopupComponent implements OnInit, OnDestroy {

    modalRef: NgbModalRef;
    routeSub: any;

    constructor (
        private route: ActivatedRoute,
        private comentarioPopupService: ComentarioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe(params => {
            this.modalRef = this.comentarioPopupService
                .open(ComentarioDeleteDialogComponent, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
