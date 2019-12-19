import {BrowserModule} from "@angular/platform-browser";
import {APP_INITIALIZER, NgModule} from "@angular/core";
import {BsDropdownModule} from "ngx-bootstrap/dropdown";
import {TooltipModule} from "ngx-bootstrap/tooltip";
import {ModalModule} from "ngx-bootstrap/modal";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PaginationModule} from 'ngx-bootstrap/pagination';

import {AppComponent} from "./app.component";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {Router, RouterModule, Routes} from "@angular/router";
import {HeaderComponent} from './components/header/header.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {CategoryService} from "./services/category.service";
import {CategoryComponent} from './components/category/category.component';
import {UserService} from "./services/user.service";
import {QuizComponent} from './components/quiz/quiz.component';
import {NewQuizComponent} from './components/new-quiz/new-quiz.component';
import {UserEditingComponent} from './components/admin/user-editing/user-editing.component';
import {CategoryEditingComponent} from './components/admin/category-editing/category-editing.component';
import {LeaderRatingComponent} from './components/leader-rating/leader-rating.component';
import {UserProfileComponent} from './components/user-profile/user-profile.component';
import {QuizService} from "./services/quiz.service";
import {QuizPassingComponent} from './components/quiz-passing/quiz-passing.component';
import {MyquizComponent} from './components/myquiz/myquiz.component';
import {AnswerService} from "./services/answer.service";
import {AuthInterceptor} from "./services/token.interceptor";
import {StatisticsService} from "./services/statistics.service";
import {RatingService} from "./services/rating.service";
import {ErrorHandlingInterceptor} from "./services/error.handling.interceptor";
import {NotFoundComponent} from './components/error-handles/not-found/not-found.component';
import {ForbiddenComponent} from './components/error-handles/forbidden/forbidden.component';
import {UserWithToken} from "./models/userWithToken";
import {Observable, Subscription} from "rxjs";
import {User} from "./models/user";
import {StatisticsComponent} from './components/admin/statistics/statistics.component';
import {QuizEditingComponent} from './components/admin/quiz-editing/quiz-editing.component';
import {RoleGuard} from "./guards/route.guard";
import {AuthGuard} from "./guards/auth.guard";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {
    path: 'login', component: LoginComponent, canActivate: [AuthGuard]
  },
  {
    path: 'register', component: RegisterComponent, canActivate: [AuthGuard]
  },
  {path: 'quiz', component: QuizComponent},
  {
    path: 'newQuiz', component: NewQuizComponent, canActivate: [RoleGuard],
    data: {role: 'user'}
  },
  {
    path: 'userEditing', component: UserEditingComponent, canActivate: [RoleGuard],
    data: {role: 'admin'}
  },
  {
    path: 'categoryEditing', component: CategoryEditingComponent, canActivate: [RoleGuard],
    data: {role: 'admin'}
  },
  {
    path: 'userProfile', component: UserProfileComponent, canActivate: [RoleGuard],
    data: {role: 'user'}
  },
  {path: 'passQuiz', component: QuizPassingComponent},
  {
    path: 'myQuiz', component: MyquizComponent, canActivate: [RoleGuard],
    data: {role: 'user'}
  },
  {path: 'notFound', component: NotFoundComponent},
  {path: 'forbidden', component: ForbiddenComponent},
  {
    path: 'statistics', component: StatisticsComponent, canActivate: [RoleGuard],
    data: {role: 'admin'}
  },
  {
    path: 'quizEditing', component: QuizEditingComponent, canActivate: [RoleGuard],
    data: {role: 'admin'}
  },
  {path: '**', component: NotFoundComponent}
]

export function initApp(http: HttpClient, user: UserService) {
  if (localStorage.getItem("token")) {
    return () => {
      return http.post<User>('/api/userLoginByToken', localStorage.getItem("token"))
        .toPromise()
        .then((resp) => {
          user.currentUser = resp as User;
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
    BrowserAnimationsModule,
    PaginationModule.forRoot()
  ],
  providers: [
    CategoryService,
    UserService,
    QuizService,
    AnswerService,
    StatisticsService,
    RatingService,
    RoleGuard,
    AuthGuard,
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
      deps: [HttpClient, UserService]
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
