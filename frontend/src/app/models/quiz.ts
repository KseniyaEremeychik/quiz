import {Question} from "./question";

export class Quiz {
  id: string;
  categoryId: string;
  categoryName: string;
  name: string;
  questionNumber: string;
  //time: string;
  creationDate: string;
  userId: string;
  userName: string;
  questions: Question[];
}
