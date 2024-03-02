/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseResponse_boolean_ } from '../models/BaseResponse_boolean_';
import type { BaseResponse_List_TeamUserVO_ } from '../models/BaseResponse_List_TeamUserVO_';
import type { BaseResponse_List_TeamVO_ } from '../models/BaseResponse_List_TeamVO_';
import type { BaseResponse_long_ } from '../models/BaseResponse_long_';
import type { BaseResponse_Page_TeamVO_ } from '../models/BaseResponse_Page_TeamVO_';
import type { BaseResponse_Team_ } from '../models/BaseResponse_Team_';
import type { TeamAddRequest } from '../models/TeamAddRequest';
import type { TeamDeleteRequest } from '../models/TeamDeleteRequest';
import type { TeamJoinRequest } from '../models/TeamJoinRequest';
import type { TeamQueryRequest } from '../models/TeamQueryRequest';
import type { TeamQuitRequest } from '../models/TeamQuitRequest';
import type { TeamUpdateRequest } from '../models/TeamUpdateRequest';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class TeamControllerService {
    /**
     * addTeam
     * @param teamAddRequest teamAddRequest
     * @returns BaseResponse_long_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static addTeamUsingPost(
        teamAddRequest: TeamAddRequest,
    ): CancelablePromise<BaseResponse_long_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/add',
            body: teamAddRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * deleteTeam
     * @param teamDeleteRequest teamDeleteRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static deleteTeamUsingPost(
        teamDeleteRequest: TeamDeleteRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/delete',
            body: teamDeleteRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * getTeamById
     * @param id id
     * @returns BaseResponse_Team_ OK
     * @throws ApiError
     */
    public static getTeamByIdUsingGet(
        id?: number,
    ): CancelablePromise<BaseResponse_Team_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/team/get',
            query: {
                'id': id,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * joinTeam
     * @param teamJoinRequest teamJoinRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static joinTeamUsingPost(
        teamJoinRequest: TeamJoinRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/join',
            body: teamJoinRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * listMyTeam
     * @returns BaseResponse_List_TeamUserVO_ OK
     * @throws ApiError
     */
    public static listMyTeamUsingGet(): CancelablePromise<BaseResponse_List_TeamUserVO_> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/team/list/my/team',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * listTeamByPage
     * @param teamQueryRequest teamQueryRequest
     * @returns BaseResponse_Page_TeamVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static listTeamByPageUsingPost(
        teamQueryRequest: TeamQueryRequest,
    ): CancelablePromise<BaseResponse_Page_TeamVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/list/page/vo',
            body: teamQueryRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * listAllTeam
     * @param teamQueryRequest teamQueryRequest
     * @returns BaseResponse_List_TeamVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static listAllTeamUsingPost(
        teamQueryRequest: TeamQueryRequest,
    ): CancelablePromise<BaseResponse_List_TeamVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/list/vo',
            body: teamQueryRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * quitTeam
     * @param teamQuitRequest teamQuitRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static quitTeamUsingPost(
        teamQuitRequest: TeamQuitRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/quit',
            body: teamQuitRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * searchTeamByConditions
     * @param teamQueryRequest teamQueryRequest
     * @returns BaseResponse_List_TeamVO_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static searchTeamByConditionsUsingPost(
        teamQueryRequest: TeamQueryRequest,
    ): CancelablePromise<BaseResponse_List_TeamVO_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/search/team',
            body: teamQueryRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
    /**
     * updateTeam
     * @param teamUpdateRequest teamUpdateRequest
     * @returns BaseResponse_boolean_ OK
     * @returns any Created
     * @throws ApiError
     */
    public static updateTeamUsingPost(
        teamUpdateRequest: TeamUpdateRequest,
    ): CancelablePromise<BaseResponse_boolean_ | any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/team/update',
            body: teamUpdateRequest,
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }
}
