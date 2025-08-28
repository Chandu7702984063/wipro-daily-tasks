import { AppComponent } from './app.component';

describe('AppComponent Tests', () => {
  let component: AppComponent;

  beforeEach(() => {
    component = new AppComponent();   
  });

  it('Test case1', () => {
    let result = component.isPalindrome('madam');
    expect(result).toEqual(true);
  });

  it('test case2', () => {
    let result = component.isPalindrome('hello');
    expect(result).toEqual(false);
  });
});
