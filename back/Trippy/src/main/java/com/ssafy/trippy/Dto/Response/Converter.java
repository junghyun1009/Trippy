package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.*;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    
    public static List<ResponseTransport> convertTransportList(List<Transport> detailLocationList) {
        List<ResponseTransport> transports = new ArrayList<>();
        for (Transport transport : detailLocationList) {
            ResponseTransport dto = ResponseTransport.builder()
                    .transport(transport).build();
            
            transports.add(dto);
        }
        return transports;
    }
    public static List<ResponseDetailLocationDto> convertDetailLocationList(List<DetailLocation> detailLocationList) {
        List<ResponseDetailLocationDto> detailLocations = new ArrayList<>();
        for (DetailLocation detailLocation : detailLocationList) {
            ResponseDetailLocationDto dto = ResponseDetailLocationDto.builder()
                    .detailLocation(detailLocation).build();

            detailLocations.add(dto);
        }
        return detailLocations;
    }
    public static List<ResponseRouteDto> convertRouteList(List<Route> routeList) {
        List<ResponseRouteDto> routes = new ArrayList<>();
        for (Route route : routeList) {
            ResponseRouteDto dto = ResponseRouteDto.builder()
                    .route(route).build();

            routes.add(dto);
        }
        return routes;
    }
    public static List<ResponsePostCommentDto> convertPostCommentList(List<PostComment> postCommentList) {
        List<ResponsePostCommentDto> postComments = new ArrayList<>();
        for (PostComment postComment : postCommentList) {
            ResponsePostCommentDto dto = ResponsePostCommentDto.builder()
                    .postComment(postComment).build();

            postComments.add(dto);
        }
        return postComments;
    }

    public static List<Transport> convertTransportsToPostTransports(List<PostTransport> postTransports) {
        List<Transport> transports = new ArrayList<>();
        for (PostTransport postTransport : postTransports) {
            transports.add(postTransport.getTransport());
        }
        return transports;
    }


    }
