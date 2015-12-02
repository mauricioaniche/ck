/**
 * Copyright (C) 2011 Brian Ferris <bdferris@onebusaway.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lcom;

import java.io.Serializable;
import java.util.List;

import org.onebusaway.geospatial.model.CoordinatePoint;
import org.onebusaway.transit_data.model.StopBean;
import org.onebusaway.transit_data.model.schedule.FrequencyBean;
import org.onebusaway.transit_data.model.service_alerts.ServiceAlertBean;

public final class TripStatusBean implements Serializable {

  private static final long serialVersionUID = 2L;

  /****
   * These are fields that we can supply from schedule data
   ****/

  private TripBean activeTrip;

  private int blockTripSequence = -1;

  private long serviceDate;

  private FrequencyBean frequency;

  private double scheduledDistanceAlongTrip = Double.NaN;

  private double totalDistanceAlongTrip = Double.NaN;

  /****
   * These are fields that we can supply from schedule data, but also update
   * from real-time data when available
   ****/

  private String phase;

  private String status;

  private double distanceAlongTrip = Double.NaN;

  private CoordinatePoint location;

  private double orientation = Double.NaN;

  private StopBean closestStop;

  private int closestStopTimeOffset;

  private StopBean nextStop;

  private int nextStopTimeOffset;

  private double nextStopDistanceFromVehicle;

  /****
   * These are fields that we can supply only from real-time data
   ****/

  private boolean predicted = false;

  private long lastUpdateTime;

  private long lastLocationUpdateTime;

  private double lastKnownDistanceAlongTrip = Double.NaN;

  private CoordinatePoint lastKnownLocation;

  private double lastKnownOrientation = Double.NaN;

  private double scheduleDeviation;

  private String vehicleId;

  private List<ServiceAlertBean> situations;

  public TripBean getActiveTrip() {
    return activeTrip;
  }

  public void setActiveTrip(TripBean activeTrip) {
    this.activeTrip = activeTrip;
  }

  public int getBlockTripSequence() {
    return blockTripSequence;
  }

  public void setBlockTripSequence(int blockTripSequence) {
    this.blockTripSequence = blockTripSequence;
  }

  public long getServiceDate() {
    return serviceDate;
  }

  public void setServiceDate(long serviceDate) {
    this.serviceDate = serviceDate;
  }

  public FrequencyBean getFrequency() {
    return frequency;
  }

  public void setFrequency(FrequencyBean frequency) {
    this.frequency = frequency;
  }

  /**
   * The vehicle's scheduled distance along the trip.
   * 
   * @return distance, in meters
   */
  public double getScheduledDistanceAlongTrip() {
    return scheduledDistanceAlongTrip;
  }

  public void setScheduledDistanceAlongTrip(double scheduledDistanceAlongTrip) {
    this.scheduledDistanceAlongTrip = scheduledDistanceAlongTrip;
  }

  public double getTotalDistanceAlongTrip() {
    return totalDistanceAlongTrip;
  }

  public void setTotalDistanceAlongTrip(double totalDistanceAlongTrip) {
    this.totalDistanceAlongTrip = totalDistanceAlongTrip;
  }

  public String getPhase() {
    return phase;
  }

  public void setPhase(String phase) {
    this.phase = phase;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return true if {@link #getDistanceAlongTrip()} has been set
   */
  public boolean isDistanceAlongTripSet() {
    return !Double.isNaN(distanceAlongTrip);
  }

  /**
   * See description in {@link #getDistanceAlongTrip()}.
   * 
   * @param distanceAlongTrip
   */
  public void setDistanceAlongTrip(double distanceAlongTrip) {
    this.distanceAlongTrip = distanceAlongTrip;
  }

  /**
   * The vehicle's distance along the trip.
   * 
   * @return distance, in meters
   */
  public double getDistanceAlongTrip() {
    return distanceAlongTrip;
  }

  public CoordinatePoint getLocation() {
    return location;
  }

  public void setLocation(CoordinatePoint location) {
    this.location = location;
  }

  public boolean isOrientationSet() {
    return !Double.isNaN(orientation);
  }

  /**
   * In degrees, 0º is East, 90º is North, 180º is West, and 270º is South
   */
  public double getOrientation() {
    return orientation;
  }

  public void setOrientation(double orientation) {
    this.orientation = orientation;
  }

  public StopBean getClosestStop() {
    return closestStop;
  }

  public void setClosestStop(StopBean closestStop) {
    this.closestStop = closestStop;
  }

  /**
   * The time offset, in seconds, from the closest stop to the current position
   * of the transit vehicle among the stop times of the current trip. If the
   * number is positive, the stop is coming up. If negative, the stop has
   * already been passed.
   * 
   * @return time, in seconds
   */
  public int getClosestStopTimeOffset() {
    return closestStopTimeOffset;
  }

  /**
   * See description in {@link #getClosestStopTimeOffset()}.
   * 
   * @param closestStopTimeOffset the time offset from the closest stop, in
   *          seconds
   */
  public void setClosestStopTimeOffset(int closestStopTimeOffset) {
    this.closestStopTimeOffset = closestStopTimeOffset;
  }

  public StopBean getNextStop() {
    return nextStop;
  }

  public void setNextStop(StopBean nextStop) {
    this.nextStop = nextStop;
  }

  /**
   * The time offset, in seconds, from the next stop to the current position of
   * the transit vehicle according to the schedule.
   * 
   * @return time, in seconds
   */
  public int getNextStopTimeOffset() {
    return nextStopTimeOffset;
  }

  public void setNextStopTimeOffset(int nextStopTimeOffset) {
    this.nextStopTimeOffset = nextStopTimeOffset;
  }

  public double getNextStopDistanceFromVehicle() {
    return nextStopDistanceFromVehicle;
  }

  public void setNextStopDistanceFromVehicle(double d) {
    this.nextStopDistanceFromVehicle = d;
  }

  /**
   * @return true if there is an real-time data for this trip, whether
   *         prediction or location
   */
  public boolean isPredicted() {
    return predicted;
  }

  public void setPredicted(boolean predicted) {
    this.predicted = predicted;
  }

  public void setLastUpdateTime(long time) {
    this.lastUpdateTime = time;
  }

  /**
   * @return the time we last heard from the bus (Unix-time)
   */
  public long getLastUpdateTime() {
    return lastUpdateTime;
  }

  /**
   * @return the time we last received a location update from the bus
   *         (Unix-time)
   */
  public long getLastLocationUpdateTime() {
    return lastLocationUpdateTime;
  }

  public void setLastLocationUpdateTime(long lastLocationUpdateTime) {
    this.lastLocationUpdateTime = lastLocationUpdateTime;
  }

  public boolean isLastKnownDistanceAlongTripSet() {
    return !Double.isNaN(lastKnownDistanceAlongTrip);
  }

  public double getLastKnownDistanceAlongTrip() {
    return lastKnownDistanceAlongTrip;
  }

  public void setLastKnownDistanceAlongTrip(double lastKnownDistanceAlongTrip) {
    this.lastKnownDistanceAlongTrip = lastKnownDistanceAlongTrip;
  }

  public CoordinatePoint getLastKnownLocation() {
    return lastKnownLocation;
  }

  public void setLastKnownLocation(CoordinatePoint lastKnownLocation) {
    this.lastKnownLocation = lastKnownLocation;
  }

  public boolean isLastKnownOrientationSet() {
    return !Double.isNaN(lastKnownOrientation);
  }

  public double getLastKnownOrientation() {
    return lastKnownOrientation;
  }

  public void setLastKnownOrientation(double lastKnownOrientation) {
    this.lastKnownOrientation = lastKnownOrientation;
  }

  public boolean isScheduleDeviationSet() {
    return !Double.isNaN(scheduleDeviation);
  }

  public double getScheduleDeviation() {
    return scheduleDeviation;
  }

  public void setScheduleDeviation(double scheduleDeviation) {
    this.scheduleDeviation = scheduleDeviation;
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public void setVehicleId(String vehicleId) {
    this.vehicleId = vehicleId;
  }

  public List<ServiceAlertBean> getSituations() {
    return situations;
  }

  public void setSituations(List<ServiceAlertBean> situations) {
    this.situations = situations;
  }
}