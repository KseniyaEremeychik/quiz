import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {CategoryService} from "./services/category.service";
import { CategoryComponent } from './components/category/category.component';
import {UserService} from "./services/user.service";
import { QuizComponent } from './components/quiz/quiz.component';
import { NewQuizComponent } from './components/new-quiz/new-quiz.component';
import { UserEditingComponent } from './components/admin/user-editing/user-editing.component';
import { CategoryEditingComponent } from './components/admin/category-editing/category-editing.component';
import { LeaderRatingComponent } from './components/leader-rating/leader-rating.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import {QuizService} from "./services/quiz.service";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'quiz', component: QuizComponent},
  {path: 'newQuiz', component: NewQuizComponent},
  {path: 'userEditing', component: UserEditingComponent},
  {path: 'categoryEditing', component: CategoryEditingComponent},
  {path: 'newQuiz', component: NewQuizComponent},
  {path: 'userProfile', component: UserProfileComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    CategoryComponent,
    QuizComponent,
    NewQuizComponent,
    UserEditingComponent,
    CategoryEditingComponent,
    LeaderRatingComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes),
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    CategoryService,
    UserService,
    QuizService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
