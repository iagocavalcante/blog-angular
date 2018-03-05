import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { ComentarioComponent } from './comentario.component';
import { ComentarioDetailComponent } from './comentario-detail.component';
import { ComentarioPopupComponent } from './comentario-dialog.component';
import { ComentarioDeletePopupComponent } from './comentario-delete-dialog.component';

import { Principal } from '../../shared';


export const comentarioRoute: Routes = [
  {
    path: 'comentario',
    component: ComentarioComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Comentarios'
    }
  }, {
    path: 'comentario/:id',
    component: ComentarioDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Comentarios'
    }
  }
];

export const comentarioPopupRoute: Routes = [
  {
    path: 'comentario-new',
    component: ComentarioPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Comentarios'
    },
    outlet: 'popup'
  },
  {
    path: 'comentario/:id/edit',
    component: ComentarioPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Comentarios'
    },
    outlet: 'popup'
  },
  {
    path: 'comentario/:id/delete',
    component: ComentarioDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Comentarios'
    },
    outlet: 'popup'
  }
];
