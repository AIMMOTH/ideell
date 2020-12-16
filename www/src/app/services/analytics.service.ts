import { Injectable } from '@angular/core';

declare let gtag: Function;

/**
 * https://developers.google.com/gtagjs/reference/api#event
 */
@Injectable({
  providedIn: 'root'
})
export class AnalyticsService {

  constructor() { }

  /**
   * For system events like app started
   *
   * @param actionName Name of interaction of user
   * @param valueName Name of value
   * @param value Actual integer value
   */
  public system(actionName: string, valueName: string, value: number): void {
    gtag('event', 'System', {
      event_category: actionName,
      vent_label: valueName,
      value
    });
  }

  /**
   * For user interactions like page change
   *
   * @param actionName Name of interaction of user
   */
  public behaviour(actionName: string): void {
    this.behaviourWithValue(actionName, null, null);
  }

  /**
   * For user interactions like page change
   *
   * @param actionName Name of interaction of user
   * @param valueName Name of value
   * @param value Actual integer value
   */
  public behaviourWithValue(actionName: string, valueName: string, value: number): void {
    gtag('event', 'Behaviour', {
      event_category: actionName,
      vent_label: valueName,
      value
    });
  }
}
