import { AuthGuard } from './guards/auth.guard';
import { EschoolService } from './services/eschool.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './components/header/header.component';

import {  MatTableModule, MatSelectModule, MatInputModule, 
  MatListModule,   MatButtonModule,
  MatToolbarModule,    
  MatDatepickerModule, MatDialogModule, MatStepperModule, MatExpansionModule,    
  MatTabsModule, ShowOnDirtyErrorStateMatcher, ErrorStateMatcher } from '@angular/material';
  
 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthService } from './services/auth.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { LoginComponent } from './components/auth/login/login.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { StudentlistComponent } from './components/studentlist/studentlist.component';
import { GrouplistComponent } from './components/grouplist/grouplist.component';
import { AddToGroupComponent } from './components/studentlist/add-to-group/add-to-group.component';
import { RemoveFromGroupComponent } from './components/studentlist/remove-from-group/remove-from-group.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    SignupComponent,
    StudentlistComponent,
    GrouplistComponent,
    AddToGroupComponent,
    RemoveFromGroupComponent,
  ],
  imports: [
    MatListModule,
    BrowserModule,
    MatToolbarModule,
    AppRoutingModule,    
    MatInputModule,
    MatButtonModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    HttpModule,
    MatInputModule,
    ReactiveFormsModule,
    MatTabsModule, 
    MatDialogModule,
    MatSelectModule,
    MatExpansionModule,
    MatTableModule

  ],
  providers: [ 
    AuthService,
    EschoolService,
    AuthGuard
  ],
  bootstrap: [AppComponent],
  entryComponents:[
    AddToGroupComponent,
    RemoveFromGroupComponent
  ]
})
export class AppModule { }
