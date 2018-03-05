import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PaginationUtil } from 'ng-jhipster';

import { InfoComponent } from './info.component';
import { InfoDetailComponent } from './info-detail.component';
import { InfoPopupComponent } from './info-dialog.component';
import { InfoDeletePopupComponent } from './info-delete-dialog.component';

import { Principal } from '../../shared';


export const infoRoute: Routes = [
  {
    path: 'info',
    component: InfoComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Infos'
    }
  }, {
    path: 'info/:id',
    component: InfoDetailComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Infos'
    }
  }
];

export const infoPopupRoute: Routes = [
  {
    path: 'info-new',
    component: InfoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Infos'
    },
    outlet: 'popup'
  },
  {
    path: 'info/:id/edit',
    component: InfoPopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Infos'
    },
    outlet: 'popup'
  },
  {
    path: 'info/:id/delete',
    component: InfoDeletePopupComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Infos'
    },
    outlet: 'popup'
  }
];
