import { reactive, h } from 'vue'
import IconFile from '../components/icons/IconFile.vue'
import IconSearch from '../components/icons/IconSearch.vue'
import IconUser from '../components/icons/IconUser.vue'
import { CodeLayoutConfig, CodeLayoutInstance, CodeLayoutPanelInternal } from 'vue-code-layout'

function loadLayout(codeLayout: CodeLayoutInstance): CodeLayoutPanelInternal[] {
    const groupUser = codeLayout.addGroup(
        {
            title: 'User',
            tooltip: 'User',
            name: 'user',
            iconLarge: () => h(IconUser)
        },
        'primarySideBar'
    )
    const groupExplorer = codeLayout.addGroup(
        {
            title: 'Explorer',
            tooltip: 'Explorer',
            name: 'explorer',
            //   badge: '2',
            iconLarge: () => h(IconFile)
        },
        'primarySideBar'
    )

    groupExplorer.addPanel({
      title: 'Search',
      tooltip: 'Search',
      name: 'explorer.search',
      noHide: true,
      startOpen: true,
      size: 130
    });
    groupExplorer.addPanel({
      title: 'Algorithm Repositories',
      tooltip: 'Algorithms',
      name: 'explorer.algorithms',
      noHide: true,
      startOpen: true
    });
    groupExplorer.addPanel({
      title: 'Algorithm Infomation',
      tooltip: 'Info',
      name: 'explorer.info',
      noHide: true,
      startOpen: true,
      size: 180
    });

    const bottomGroup = codeLayout.getRootGrid('bottomPanel')
    bottomGroup.addPanel({
        title: 'TERMINAL',
        tooltip: 'Terminal',
        name: 'bottom.terminal'
    })

    const groups = [groupUser, groupExplorer]
    return groups
}

const config = reactive<CodeLayoutConfig>({
    primarySideBarSwitchWithActivityBar: true,
    primarySideBarPosition: 'left',
    primarySideBarWidth: 40,
    primarySideBarMinWidth: 170,
    activityBarPosition: 'side',
    secondarySideBarWidth: 20,
    secondarySideBarMinWidth: 170,
    bottomPanelHeight: 25,
    bottomPanelMinHeight: 40,
    bottomAlignment: 'center',
    panelHeaderHeight: 50,
    panelMinHeight: 150,
    titleBar: true,
    titleBarShowCustomizeLayout: false,
    activityBar: true,
    primarySideBar: true,
    secondarySideBar: false,
    bottomPanel: false,
    statusBar: true,
    menuBar: true,
    bottomPanelMaximize: false
})

const menuData = {
    x: 0,
    y: 0,
    items: [
        {
            label: 'File',
            children: [
                { label: 'New' },
                { label: 'Open' },
                { label: 'Save', divided: true },
                { label: 'Save as...' },
                { label: 'Close' },
                { label: 'Exit' }
            ]
        },
        {
            label: 'Edit',
            children: [
                { label: 'Undo' },
                { label: 'Redo' },
                { label: 'Cut', divided: true },
                { label: 'Copy' },
                { label: 'Find', divided: true },
                { label: 'Replace' }
            ]
        },
        {
            label: 'View',
            children: [
                { label: 'Zoom in' },
                { label: 'Zoom out' },
                { label: 'Reset zoom' },
                { label: 'Full screent', divided: true },
                { label: 'Find', divided: true },
                { label: 'Replace' }
            ]
        },
        {
            label: 'Help',
            children: [{ label: 'About' }]
        }
    ],
    zIndex: 3,
    minWidth: 230
}

export { loadLayout, config, menuData }
