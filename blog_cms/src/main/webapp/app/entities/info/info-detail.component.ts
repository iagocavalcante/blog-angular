import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Info } from './info.model';
import { InfoService } from './info.service';

@Component({
    selector: 'jhi-info-detail',
    templateUrl: './info-detail.component.html'
})
export class InfoDetailComponent implements OnInit, OnDestroy {

    info: Info;
    private subscription: any;

    constructor(
        private infoService: InfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe(params => {
            this.load(params['id']);
        });
    }

    load (id) {
        this.infoService.find(id).subscribe(info => {
            this.info = info;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }

}
