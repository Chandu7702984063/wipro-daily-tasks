import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { SecondcompComponent } from './app/secondcomp/secondcomp.component';
import { InlinecompComponent } from './app/inlinecomp/inlinecomp.component';

bootstrapApplication(InlinecompComponent)
  .catch((err) => console.error(err));
