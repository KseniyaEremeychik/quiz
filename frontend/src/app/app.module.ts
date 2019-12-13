import { BrowserModule } from "@angular/platform-browser";
import {APP_INITIALIZER, NgModule} from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from "./app.component";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
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
import { QuizPassingComponent } from './components/quiz-passing/quiz-passing.component';
import { MyquizComponent } from './components/myquiz/myquiz.component';
import {AnswerService} from "./services/answer.service";
import {AuthInterceptor} from "./services/token.interceptor";
import {StatisticsService} from "./services/statistics.service";
import {RatingService} from "./services/rating.service";
import {ErrorHandlingInterceptor} from "./services/error.handling.interceptor";
import { NotFoundComponent } from './components/error-handles/not-found/not-found.component';
import { ForbiddenComponent } from './components/error-handles/forbidden/forbidden.component';
import {UserWithToken} from "./models/userWithToken";
import {Observable, Subscription} from "rxjs";
import {User} from "./models/user";
import { StatisticsComponent } from './components/admin/statistics/statistics.component';
import { QuizEditingComponent } from './components/admin/quiz-editing/quiz-editing.component';

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'quiz', component: QuizComponent},
  {path: 'newQuiz', component: NewQuizComponent},
  {path: 'userEditing', component: UserEditingComponent},
  {path: 'categoryEditing', component: CategoryEditingComponent},
  {path: 'newQuiz', component: NewQuizComponent},
  {path: 'userProfile', component: UserProfileComponent},
  {path: 'passQuiz', component: QuizPassingComponent},
  {path: 'myQuiz', component: MyquizComponent},
  {path: 'notFound', component: NotFoundComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {path: 'statistics', component: StatisticsComponent},
  {path: 'quizEditing', component: QuizEditingComponent}
]

/*export function initApp(http: HttpClient, user: UserService) {
  return () => {
    return http.post<UserWithToken>('/api/userLogin', {"email" : "anton.yerameichyk@gmail.com", "password" : "12345678"})
      .toPromise()
      .then((resp) => {
        let userWithToken = resp as UserWithToken;
        console.log(userWithToken.user);
        setTimeout(() => {
          user.currentUser = userWithToken.user;
        }, 1000);
      });
  };
}*/

export function initApp(http: HttpClient, user: UserService) {
  if(localStorage.getItem("token")) {
    return () => {
      return http.post<User>('/api/userLoginByToken', localStorage.getItem("token"))
        .toPromise()
        .then((resp) => {
          //console.log(resp as User);
          //localStorage.setItem("userName", resp.userName);
          //user.currentUser.userName = localStorage.getItem("userName");
          /*user.currentUser = resp as User;
          console.log(user.currentUser);*/
        });
    };
  } else {
    return () => {
      return new Promise((resolve) => {
        setTimeout(() => {
          resolve();
        }, 50);
      });
    };
  }
}

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
    UserProfileComponent,
    QuizPassingComponent,
    MyquizComponent,
    NotFoundComponent,
    ForbiddenComponent,
    StatisticsComponent,
    QuizEditingComponent
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
    QuizService,
    AnswerService,
    StatisticsService,
    RatingService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorHandlingInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: initApp,
      multi: true,
      deps: [HttpClient]
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
