import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { Http, BaseRequestOptions } from '@angular/http';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { DateUtils, DataUtils } from 'ng-jhipster';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { InfoDetailComponent } from '../../../../../../main/webapp/app/entities/info/info-detail.component';
import { InfoService } from '../../../../../../main/webapp/app/entities/info/info.service';
import { Info } from '../../../../../../main/webapp/app/entities/info/info.model';

describe('Component Tests', () => {

    describe('Info Management Detail Component', () => {
        let comp: InfoDetailComponent;
        let fixture: ComponentFixture<InfoDetailComponent>;
        let service: InfoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                declarations: [InfoDetailComponent],
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
                    InfoService
                ]
            }).overrideComponent(InfoDetailComponent, {
                set: {
                    template: ''
                }
            }).compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InfoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InfoService);
        });


        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Info(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.info).toEqual(jasmine.objectContaining({id:10}));
            });
        });
    });

});
