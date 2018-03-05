import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BlogCmsSharedModule } from '../../shared';

import {
    ComentarioService,
    ComentarioPopupService,
    ComentarioComponent,
    ComentarioDetailComponent,
    ComentarioDialogComponent,
    ComentarioPopupComponent,
    ComentarioDeletePopupComponent,
    ComentarioDeleteDialogComponent,
    comentarioRoute,
    comentarioPopupRoute,
} from './';

let ENTITY_STATES = [
    ...comentarioRoute,
    ...comentarioPopupRoute,
];

@NgModule({
    imports: [
        BlogCmsSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        ComentarioComponent,
        ComentarioDetailComponent,
        ComentarioDialogComponent,
        ComentarioDeleteDialogComponent,
        ComentarioPopupComponent,
        ComentarioDeletePopupComponent,
    ],
    entryComponents: [
        ComentarioComponent,
        ComentarioDialogComponent,
        ComentarioPopupComponent,
        ComentarioDeleteDialogComponent,
        ComentarioDeletePopupComponent,
    ],
    providers: [
        ComentarioService,
        ComentarioPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BlogCmsComentarioModule {}
