import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ComentarioDetailComponent } from '../../../../../../main/webapp/app/entities/comentario/comentario-detail.component';
import { ComentarioService } from '../../../../../../main/webapp/app/entities/comentario/comentario.service';
import { Comentario } from '../../../../../../main/webapp/app/entities/comentario/comentario.model';

describe('Component Tests', () => {

    describe('Comentario Management Detail Component', () => {
        let comp: ComentarioDetailComponent;
        let fixture: ComponentFixture<ComentarioDetailComponent>;
        let service: ComentarioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [ComentarioDetailComponent],
                providers: [
                    MockBackend,
                    BaseRequestOptions,
                    DateUtils,
                    DataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    {
                        provide: Http,
                        useFactory: (backendInstance: MockBackend, defaultOptions: BaseRequestOptions) => {
                            return new Http(backendInstance, defaultOptions);
                        },
                        deps: [MockBackend, BaseRequestOptions]
                    },
                    ComentarioService
                ]
            }).overrideComponent(ComentarioDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ComentarioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComentarioService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Comentario(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.comentario).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
