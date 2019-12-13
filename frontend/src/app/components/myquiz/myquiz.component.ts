import {Component, OnInit, TemplateRef} from '@angular/core';
import {Quiz} from "../../models/quiz";
import {QuizService} from "../../services/quiz.service";
import {Subscription} from "rxjs";
import {UserService} from "../../services/user.service";
import {CategoryService} from "../../services/category.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";

@Component({
  selector: 'app-myquiz',
  templateUrl: './myquiz.component.html',
  styleUrls: ['./myquiz.component.css']
})
export class MyquizComponent implements OnInit {
  private modalRef: BsModalRef;
  private quizList: Quiz[] = [];
  private subscriptions: Subscription[] = [];
  private curQuizId: number;

  constructor(private quizService: QuizService,
              private userService: UserService,
              private modalService: BsModalService) { }

  ngOnInit() {
    this.getAllUsersQuiz();
  }

  public getAllUsersQuiz(): void {
    this.subscriptions.push(this.quizService.getAllQuizByUserId(this.userService.currentUser.id).subscribe((quiz) => {
      this.quizList = quiz as Quiz[];
    }));
  }

  public deleteQuiz(id: number): void {
    this.subscriptions.push(this.quizService.deleteQuiz(id).subscribe(() => {
      this.updateQuiz();
    }));
    this.modalRef.hide();
  }

  openModal(template: TemplateRef<any>, curQuiz: number) {
    this.modalRef = this.modalService.show(template);
    this.curQuizId = curQuiz;
  }

  public updateQuiz(): void {
    this.getAllUsersQuiz();
  }

  public showQuiz(quizId: number, quizName: string, userName: string): void {
    localStorage.setItem("quizId", '' + quizId);
    localStorage.setItem("quizName", quizName);
    localStorage.setItem("userName", userName);
  }
}
