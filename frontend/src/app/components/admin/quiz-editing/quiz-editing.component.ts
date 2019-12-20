import {Component, OnInit, TemplateRef} from '@angular/core';
import {PageQuiz} from "../../../models/pageQuiz";
import {QuizService} from "../../../services/quiz.service";
import {Subscription} from "rxjs";
import {Quiz} from "../../../models/quiz";
import {BsModalRef, BsModalService, PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-quiz-editing',
  templateUrl: './quiz-editing.component.html',
  styleUrls: ['./quiz-editing.component.css']
})
export class QuizEditingComponent implements OnInit {
  private modalRef: BsModalRef;
  private pageQuiz: PageQuiz;
  private quizList: Quiz[] = []
  private subscriptions: Subscription[] = [];
  private curPage: number = 1;
  private pageSize: number = 9;
  private curQuizId: number;
  private buttonText: string;
  private curQuiz: Quiz = new Quiz();
  private sortParam: string = null;

  //1 - asc, -1 - desc
  private sortFormat: number = 1;

  constructor(private quizService: QuizService,
              private modalService: BsModalService) { }

  ngOnInit() {
    if(this.quizService.status) {
      this.getQuizByStatus(this.quizService.status);
    } else {
      this.getAllQuiz(this.curPage, this.pageSize);
    }
  }

  public getAllQuiz(page: number, size: number): void {
    this.subscriptions.push(this.quizService.getAllQuiz(this.curPage-1, this.pageSize, this.sortParam, this.sortFormat).subscribe(resp => {
      this.pageQuiz = resp as PageQuiz;
      this.quizList = this.pageQuiz.content;
    }));
  }

  public getQuiz(quizId: number, quizName: string): void {
    localStorage.setItem("quizId", '' + quizId);
    localStorage.setItem("quizName", quizName);
  }

  public getQuizByStatus(status: string) {
    if(!(this.quizService.status === status)) {
      this.curPage = 1;
    }
    this.quizService.status = status;
    this.subscriptions.push(this.quizService.getAllQuizWithStatus(status, this.curPage-1, this.pageSize, this.sortParam, this.sortFormat).subscribe(resp => {
      this.pageQuiz = resp as PageQuiz;
      this.quizList = this.pageQuiz.content;
    }));
  }

  pageChanged(event: PageChangedEvent) {
    this.curPage = event.page;
    console.log()
    if(this.quizService.status) {
      this.getQuizByStatus(this.quizService.status);
    } else {
      this.getAllQuiz(this.curPage-1, this.pageSize);
    }
  }

  openModal(template: TemplateRef<any>, curQuiz: number) {
    this.modalRef = this.modalService.show(template);
    this.curQuizId = curQuiz;
  }

  openEditModal(buttonText: string, quiz: Quiz, template: TemplateRef<any>) {
    this.buttonText = buttonText;
    this.curQuiz = quiz;
    this.modalRef = this.modalService.show(template);
  }

  public deleteQuiz(id: number): void {
    this.subscriptions.push(this.quizService.deleteQuiz(id).subscribe(() => {
      this.updateQuiz();
    }));
    this.modalRef.hide();
  }

  public changeStatus(): void {
    if(this.buttonText === 'Approve') {
      this.editStatus(this.curQuiz);
      this.modalRef.hide();
    } else if(this.buttonText === 'Reject') {
      this.deleteQuiz(this.curQuiz.id);
      this.modalRef.hide();
    }
  }

  public getAllFilter(): void {
    this.quizService.status = null;
    this.getAllQuiz(1, this.pageSize);
  }

  public editStatus(quiz: Quiz) {
    quiz.isConfirmed = 'approved';
    this.subscriptions.push(this.quizService.editStatus(quiz).subscribe(resp => {
      console.log(resp);
    }));
  }

  private updateQuiz(): void {
    if(this.quizService.status) {
      this.getQuizByStatus(this.quizService.status);
    } else {
      this.getAllQuiz(this.curPage, this.pageSize);
    }
  }

  public setSortParam(param: string): void {
    if(this.sortParam === param) {
      this.sortFormat = -this.sortFormat;
    } else {
      this.sortParam = param;
    }
    this.updateQuiz();
  }
}
