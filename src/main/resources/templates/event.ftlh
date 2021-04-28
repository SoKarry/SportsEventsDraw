<#import "macros/cmn.ftlh" as p>
<link href='https://tlab.pro/dist/0.b831ee28978f1ca3371a.styles.css' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,500,700&subset=cyrillic,latin' rel='stylesheet' type='text/css'>

<@p.page>
<#--редактирование события доступно только для владельца-->
<#if isCurrentUser>
    <#include "macros/eventEdit.ftlh" />
</#if>
</@p.page>

<H1 class="text-center"><#if event??>${event.name}</#if></H1>
<#if event.draw.id=1>
<div id="tournamentBodyWrapper">
    <div data-v-925ca4a2="">
        <div data-v-925ca4a2="" id="tournamentBody" class="is-hidden-mobile size-1x">
            <div data-v-925ca4a2="" class="columns single-elimination">
                <div data-v-925ca4a2="" class="column round1" style="min-width: 266px;">
                    <div data-v-925ca4a2="" class="round-header">1/${games?size}</div>
                    <#list games as game>
                        <div data-v-925ca4a2="" class="pair pair_${(game?index%2)+1}">
                            <div data-v-58dea290="" data-v-925ca4a2="" class="player player1">
                                <figure data-v-58dea290="" class="image"><img data-v-58dea290="" src="/img/icon.png"></figure>
                                <span data-v-58dea290="" title="Edit participant" class="is-uppercase text-ellipsis is-clickable_ winner" style="width: 140px;">
			${game.player1Name}
		 </span>
                                <div data-v-58dea290="" title="Edit match score " class="has-background-yellow_ is-clickable_"></div>
                            </div>
                            <div data-v-58dea290="" data-v-925ca4a2="" class="player player2">
                                <figure data-v-58dea290="" class="image"><img data-v-58dea290="" src="/img/icon.png"></figure>
                                <span data-v-58dea290="" title="Edit participant" class="is-uppercase text-ellipsis is-clickable_ winner" style="width: 140px;">
			${game.player2Name}
		 </span>
                                <div data-v-58dea290="" title="Edit match score " class="has-background-yellow_ is-clickable_"></div>
                            </div>
                        </div>
                    </#list>
                </div>


                <#list [2, 4, 8, 16, 32, 64, 128, 256, 512] as i>

                    <#if ((games?size)/i >= 1)>
                    <div data-v-925ca4a2="" class="column round${i?index+2}<#if ((games?size)/i == 1)> final</#if>" style="min-width: 266px;">
                    <div data-v-925ca4a2="" class="round-header"><#if ((games?size)/i == 1)>ФИНАЛ<#else>1/${(games?size)/i}</#if></div>
                        <#list 0..((games?size)/i)-1 as game>
                                <div data-v-925ca4a2="" class="pair pair_${(game?index%2)+1}">
                                    <div data-v-58dea290="" data-v-925ca4a2="" class="player player1">
                                        <figure data-v-58dea290="" class="image"><img data-v-58dea290="" src="/img/icon.png"></figure>
                                        <span data-v-58dea290="" title="undefined" class="is-uppercase text-ellipsis winner" style="width: 140px;">
				                        </span>
                                        <div data-v-58dea290="" title="" class="has-background-yellow_"></div>
                                    </div>
                                    <div data-v-58dea290="" data-v-925ca4a2="" class="player player2">
                                        <figure data-v-58dea290="" class="image"><img data-v-58dea290="" src="/img/icon.png"></figure>
                                        <span data-v-58dea290="" title="undefined" class="is-uppercase text-ellipsis winner" style="width: 140px;">
				                        </span>
                                        <div data-v-58dea290="" title="" class="has-background-yellow_"></div>
                                    </div>
                                </div>
                            </#list>
                        </div>
                    </#if>
                </#list>

            </div>
        </div>
    </div>
</div>
<#elseif event.draw.id=2>
    <div id="tournamentBodyWrapper">
        <div data-v-8d4ae3dc="" class="round-table" id="tournamentBody">
            <div data-v-8d4ae3dc="" class="round-table-header has-background-black_">
                <div data-v-8d4ae3dc="" class="name-col" style="padding-left: 2.25rem;"></div>
                <div data-v-8d4ae3dc="" class="devider-col">|</div>
                <#list 1..players?size as pla>
                <div data-v-8d4ae3dc="">
                    ${pla}
                </div>
                </#list>
                <div data-v-8d4ae3dc="" class="devider-col"></div>
            </div>
            <div data-v-8d4ae3dc="" class="devider-row"></div>
            <div data-v-8d4ae3dc="" class="round-table-body">
                <div data-v-8d4ae3dc="" class="body-cell body-cell-player">
                    <#list players as player>
                    <div data-v-8d4ae3dc="" class="">
                        <span data-v-8d4ae3dc="" class="player-index">${player?index+1}</span>
                        <figure data-v-8d4ae3dc="" class="image"><img data-v-8d4ae3dc="" src="/img/iconk.png" class="is-rounded is-clickable_"></figure>
                        <span data-v-8d4ae3dc="" title="Edit participant" class="is-clickable_">
                          ${player.plname}
                    </span>
                    </div>
                    </#list>
                </div>
                <div data-v-8d4ae3dc="" class="body-cell-opener">
                    <div data-v-8d4ae3dc=""><span data-v-8d4ae3dc="" class="opener-btn opener-btn-left"></span></div>
                </div>
                <#list 1..players?size as sz>
                <div data-v-8d4ae3dc="" class="body-cell body-cell-score">
                    <#list players as player>
                    <div data-v-8d4ae3dc="" class="has-text-weight-normal_ is-clickable_"><#if player?index+1 == sz>-</#if></div>
                    </#list>
                </div>
                </#list>
            </div>
        </div>
    </div>
<#else>
</#if>