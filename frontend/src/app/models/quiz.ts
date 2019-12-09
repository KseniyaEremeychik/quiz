import {Question} from "./question";

export class Quiz {
  id: number;
  categoryId: number;
  categoryName: string;
  name: string;
  questionNumber: number;
  isConfirmed: string;
  //time: string;
  creationDate: string;
  userId: number;
  userName: string;
  questions: Question[];
}
