import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, FormGroup } from '@angular/forms';
import { HeaderInterceptor } from './header-interceptor';

import { HomeComponent } from './routes/home/home.component';
import { TeamsComponent } from './routes/teams/teams.component';
import { FormsComponent } from './routes/teams/forms/forms.component';
import { ListComponent } from './routes/teams/list/list.component';
import { HistoryComponent } from './routes/history/history.component';
import { HeaderComponent } from './routes/header/header.component';

import {MatButtonModule} from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { ToastrModule } from 'ngx-toastr';

import { TeamsService } from './service/teams-service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TeamsComponent,
    FormsComponent,
    ListComponent,
    HistoryComponent,
    HeaderComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatSelectModule,    
    MatInputModule,
    MatIconModule,
    MatDialogModule,
    MatCardModule,
    FormsModule,
    MatTableModule,
    MatToolbarModule,
    MatSidenavModule,
    HttpClientModule,
    MatSnackBarModule,
    ToastrModule.forRoot()
  ],
  providers: [
    TeamsService,
    { provide: HTTP_INTERCEPTORS, useClass: HeaderInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
