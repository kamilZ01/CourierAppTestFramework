package org.automation.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public record Points(
        String href,
        String country,
        String name,
        List<String> type,
        String status,
        Location location,
        @JsonProperty("location_type") String locationType,
        @JsonProperty("location_date") Object locationDate,
        @JsonProperty("location_description") String locationDescription,
        @JsonProperty("location_description_1") String locationDescription1,
        @JsonProperty("location_description_2") String locationDescription2,
        Integer distance,
        @JsonProperty("opening_hours") String openingHours,
        Address address,
        @JsonProperty("address_details") AddressDetails addressDetails,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("payment_point_descr") String paymentPointDescription,
        List<String> functions,
        @JsonProperty("partner_id") Integer partnerId,
        @JsonProperty("is_next") Boolean isNext,
        @JsonProperty("payment_available") Boolean paymentAvailable,
        @JsonProperty("payment_type") Map<String, String> paymentType,
        Integer virtual,
        @JsonProperty("recommended_low_interest_box_machines_list") String[] recommendedLowInterestBoxMachinesList,
        @JsonProperty("apm_doubled") Object apmDoubled,
        @JsonProperty("location_247") Boolean location247,
        @JsonProperty("operating_hours_extended") Map<String, Object> operatingHoursExtended,
        String agency,
        @JsonProperty("agencies_extended") List<Agency> agenciesExtended,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("easy_access_zone") Boolean easyAccessZone,
        @JsonProperty("air_index_level") String airIndexLevel,
        @JsonProperty("physical_type") String physicalType,
        @JsonProperty("physical_type_mapped") String physicalTypeMapped,
        @JsonProperty("physical_type_description") String physicalTypeDescription,
        @JsonProperty("in_mobile_collect_excluded") Object inMobileCollectExcluded,
        @JsonProperty("in_mobile_send_excluded") Object inMobileSendExcluded,
        @JsonProperty("in_mobile_return_excluded") Object inMobileReturnExcluded,
        @JsonProperty("express_delivery_send") Boolean expressDeliverySend,
        @JsonProperty("express_delivery_collect") Boolean expressDeliveryCollect,
        @JsonProperty("delivery_area_id") String deliveryAreaId,
        @JsonProperty("micro_area_id") String microAreaId,
        @JsonProperty("agency_code") String agencyCode,
        @JsonProperty("d2d_courier_area") String d2dCourierArea,
        @JsonProperty("d2d_courier_micro_area") String d2dCourierMicroArea,
        @JsonProperty("locker_availability") LockerAvailability lockerAvailability,
        @JsonProperty("supported_locker_temperatures") List<Integer> supportedLockerTemperatures
) implements DataObjectToDTO<PointsDTO> {

    @Override
    public PointsDTO toDTO() {
        return new PointsDTO(name, addressDetails.postCode(), location);
    }
}
