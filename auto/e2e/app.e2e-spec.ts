import { ManagePage } from './app.po';

describe('manage App', function() {
  let page: ManagePage;

  beforeEach(() => {
    page = new ManagePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
